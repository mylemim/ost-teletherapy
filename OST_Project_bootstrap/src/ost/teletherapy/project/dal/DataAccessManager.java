package ost.teletherapy.project.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import ost.teletherapy.project.semantics.Arousal;
import ost.teletherapy.project.semantics.MultimediaSemantics;
import ost.teletherapy.project.semantics.MultimediaType;
import ost.teletherapy.project.semantics.SemanticMeaning;
import ost.teletherapy.project.user.User;
import ost.teletherapy.project.user.UserType;

public class DataAccessManager {

	private static Connection connection = null;
	private static Boolean isDriverLoaded = false;

	private static void openConnectionToDatabase()
			throws ClassNotFoundException, SQLException, NamingException {
		if (!isDriverLoaded) {
			isDriverLoaded = true;
			Class.forName("org.sqlite.JDBC");
		}

		javax.naming.InitialContext ctx = new javax.naming.InitialContext();
		javax.sql.DataSource ds = (javax.sql.DataSource) ctx
				.lookup("jdbc/SQLite");
		connection = ds.getConnection();
	}

	private static void openTransactionToDatabase()
			throws ClassNotFoundException, SQLException, NamingException {
		openConnectionToDatabase();
		connection.setAutoCommit(false);
	}

	private static void closeConnection() throws SQLException {
		connection.close();
	}

	public static List<SemanticMeaning> getAllSemantics() {

		List<SemanticMeaning> allSemantics = new ArrayList<SemanticMeaning>();

		try {
			openConnectionToDatabase();

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement
					.executeQuery("select * from semanticmeaning");

			while (resultSet.next()) {
				String newMeaning = resultSet.getString("name");
				int id = resultSet.getInt("id");
				allSemantics.add(new SemanticMeaning(id, newMeaning));
			}

			closeConnection();
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			e.printStackTrace();
		}

		return allSemantics;
	}

	public static void addNewSemanticMeaning(SemanticMeaning semantic) {

		try {
			if (!getAllSemantics().contains(semantic)) {

				openTransactionToDatabase();
				PreparedStatement statement = connection
						.prepareStatement("Insert into semanticmeaning(name) values (?)");
				statement.setString(1, semantic.getSemanticMeaning());
				statement.execute();
				connection.commit();
				connection.setAutoCommit(true);
			}
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			// rollback if there's a connection
			if (connection != null)
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			e.printStackTrace();
		}
	}

	public static int getTypeId(MultimediaType type) {

		try {
			openConnectionToDatabase();

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement
					.executeQuery("select multimediatype.id as id from multimediatype "
							+ "where multimediatype.name = \'"
							+ type.toString() + "\'");

			// we need just the first result
			resultSet.next();

			int id = resultSet.getInt("id");

			closeConnection();

			return id;

		} catch (ClassNotFoundException | SQLException | NamingException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static List<MultimediaSemantics> getAllMultimediaSemantics() {
		List<MultimediaSemantics> allMultimediaSemantics = new ArrayList<MultimediaSemantics>();

		try {
			openConnectionToDatabase();

			Statement statement = connection.createStatement();

			// first fetch all the multimedia
			ResultSet resultSetMultimedia = statement
					.executeQuery("select multimedia.id as id, multimedia.path as path, multimediatype.name as type from multimedia "
							+ "join multimediatype on multimedia.id_type = multimediatype.id");

			while (resultSetMultimedia.next()) {
				String multimediaPath = resultSetMultimedia.getString("path");
				String typeString = resultSetMultimedia.getString("type");
				int id = resultSetMultimedia.getInt("id");
				MultimediaType type = MultimediaType.valueOf(typeString);

				MultimediaSemantics newMultimediaSemantic = new MultimediaSemantics(
						id, multimediaPath, type);

				// fetch all the semantics for the specific multimedia
				ResultSet semanticsResultSet = statement
						.executeQuery("select semanticmeaning.id as semantic_id, "
								+ "semanticmeaning.name as semantic_name "
								+ "from multimediasemantics "
								+ "join multimedia on multimediasemantics.id_multimedia=multimedia.id "
								+ "join semanticmeaning on multimediasemantics.id_semantics=semanticmeaning.id "
								+ "where multimediasemantics.id_multimedia = "
								+ id);

				while (semanticsResultSet.next()) {
					String semantic_name = resultSetMultimedia
							.getString("semantic_name");
					int semantic_id = resultSetMultimedia.getInt("semantic_id");

					newMultimediaSemantic.addSemantics(new SemanticMeaning(
							semantic_id, semantic_name));
				}

				allMultimediaSemantics.add(newMultimediaSemantic);
			}

			closeConnection();
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			e.printStackTrace();
		}

		return allMultimediaSemantics;
	}

	public static void addNewMultimediaSemantic(
			MultimediaSemantics multimediaSemantics) {

		try {
			if (!getAllMultimediaSemantics().contains(multimediaSemantics)) {

				openTransactionToDatabase();
				// first we add the multimedia entry
				PreparedStatement statementMultimediaTable = connection
						.prepareStatement("Insert into multimedia(path, id_type) values (?,?)");
				statementMultimediaTable.setString(1,
						multimediaSemantics.getPathToMedia());
				statementMultimediaTable.setInt(2,
						getTypeId(multimediaSemantics.getType()));
				statementMultimediaTable.execute();

				PreparedStatement statementMMSemanticsTable = connection
						.prepareStatement("Insert into multimediasemantics(id_multimedia, id_semantic) values (?,?)");
				statementMMSemanticsTable
						.setInt(1, multimediaSemantics.getId());

				// add and commit every entry. if we commit the first one, the
				// multimedia is added too
				for (SemanticMeaning s : multimediaSemantics.getSemantics()) {
					statementMMSemanticsTable.setInt(2, s.getId());
					statementMMSemanticsTable.execute();
					connection.commit();
				}
				connection.setAutoCommit(true);
			}
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			// rollback if there's a connection
			if (connection != null)
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return null if unsuccessful login, usertype on successful
	 */
	public static UserType loginUser(String username, String password) {

		User user = new User();

		try {
			openConnectionToDatabase();

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement
					.executeQuery("select * from user join "
							+ "usertype on user.id_type=usertype.id where "
							+ "user.username='" + username + "' "
							+ "and user.password='" + password + "'");

			resultSet.next();
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));

			if (resultSet.getString("typename").equals("therapist"))
				user.setType(UserType.Therapist);
			else if (resultSet.getString("typeName").equals("patient"))
				user.setType(UserType.Patient);
			else
				user.setType(UserType.NoType);

			closeConnection();

			return user.getType();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public static List<Arousal> getAllArousals()
	{
		// lista koju će vraćati
		List<Arousal> allArousals = new ArrayList<Arousal>();

		try
		{
			// otvara vezu
			openConnectionToDatabase();

			// pravi statement
			Statement statement = connection.createStatement();

			// dohvaća rezultate
			ResultSet resultSet = statement
					.executeQuery("select *, impulse.idimpulse as impulse from arousal JOIN impulse ON arousal.idimpulse = impulse.idimpulse");

			// iterira po rezultatima
			while (resultSet.next())
			{
				String path = resultSet.getString("path");
				String name = resultSet.getString("name");
				int impulseValue = resultSet.getInt("impulse");
				String impulseDescription = resultSet.getString("description");
				
				// stvara novi arousal
				Arousal arousal = new Arousal(path, name, impulseValue, impulseDescription);
				
				// dodaje arousal u listu
				allArousals.add(arousal);
			}

			// gasi vezu
			closeConnection();
		}
		catch(ClassNotFoundException | SQLException | NamingException e)
		{
			// logira
			e.printStackTrace();
			
			// vraća null
			return null;
		}

		// vraća što je dohvatio
		return allArousals;
	}
}
