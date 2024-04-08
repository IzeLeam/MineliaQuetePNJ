package fr.izeleam.minelia.mineliaquetepnj.utils;

import fr.izeleam.minelia.mineliaquetepnj.MineliaQuetePNJ;
import fr.izeleam.minelia.mineliaquetepnj.Quest;
import fr.izeleam.minelia.mineliaquetepnj.QuestManager;
import fr.izeleam.minelia.mineliaquetepnj.QuestPlayer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public class Database implements Listener {

  private final static Database instance = new Database();

  public static Database getInstance() {
    return instance;
  }

  private Connection connection = null;

  private Database() {
    connect();
    init();
  }

  private void connect() {
    FileConfiguration config = MineliaQuetePNJ.getInstance().getConfig();

    String host = config.getString("database.host");
    int port = config.getInt("database.port");
    String database = config.getString("database.database");
    String username = config.getString("database.username");
    String password = config.getString("database.password");

    try {
      this.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
    }
  }

  private void init() {
    String table = MineliaQuetePNJ.getInstance().getConfig().getString("database.table");
    try {
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + table + " (id SERIAL PRIMARY KEY, uuid VARCHAR(36), rarity VARCHAR(255), quest_id int, progress int);");
        statement.executeUpdate();
    } catch (java.sql.SQLException e) {
        e.printStackTrace();
    }
  }

  public void loadPlayer(QuestPlayer player) {
    String table = MineliaQuetePNJ.getInstance().getConfig().getString("database.table");
    try {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE uuid = ?;");
      statement.setString(1, player.getPlayer().getUniqueId().toString());

      ResultSet result = statement.executeQuery();
      while (result.next()) {
        Quest quest = null;

        for (Quest q : QuestManager.getInstance().getQuests(result.getString("rarity"))) {
          if (q.getId() == result.getInt("quest_id")) {
            quest = q;
            break;
          }
        }
        if (quest == null) {
          System.out.println("Quest not found");
          continue;
        }
        player.addQuest(quest, result.getInt("progress"));
      }
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
    }
  }

  public void savePlayer(QuestPlayer player) {
    String table = MineliaQuetePNJ.getInstance().getConfig().getString("database.table");
    try {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM " + table + " WHERE uuid = ?;");
      statement.setString(1, player.getPlayer().getUniqueId().toString());
      statement.executeUpdate();

      for (Pair<Quest, Integer> pair : player.getActiveQuests()) {
        statement = connection.prepareStatement("INSERT INTO " + table + " (uuid, rarity, quest_id, progress) VALUES (?, ?, ?, ?);");
        statement.setString(1, player.getPlayer().getUniqueId().toString());
        statement.setString(2, pair.getKey().getRarity());
        statement.setInt(3, pair.getKey().getId());
        statement.setInt(4, pair.getValue());
        statement.executeUpdate();
      }
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
    }
  }
}
