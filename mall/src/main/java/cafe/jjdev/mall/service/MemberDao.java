package cafe.jjdev.mall.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDao {
	//객체 종료를 위한 공통사용 코드 메서드화
    private void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if(resultSet != null) {
            try {resultSet.close();} catch(Exception exception) {exception.printStackTrace();}
        }
        if(preparedStatement != null) {
            try {preparedStatement.close();} catch(Exception exception) {exception.printStackTrace();}
        }
        if(connection != null) {
            try {connection.close();} catch(Exception exception) {exception.printStackTrace();}
        }
    }
    //데이터베이스 연결을 위한 공통사용 코드 메서드화
    private Connection getConnection() throws SQLException, NamingException {
    	Connection connection = null;
		Context initContext = new InitialContext();
		DataSource dataSource = (DataSource)initContext.lookup("java:comp/env/jdbc/mall");
		connection = dataSource.getConnection();
		return connection;
    }
	public int insertMember(Member member) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int rows = 0;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement("insert into member(id,pw,level) values(?,?,?)");
			preparedStatement.setString(1, member.getId());
			preparedStatement.setString(2, member.getPw());
			preparedStatement.setInt(3, member.getLevel());
			rows = preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.close(connection,preparedStatement ,resultSet);
		}
		return rows;
	}
	//id와 pw값을 가지는 member객체를 이용해 로그인체크를 하는 메서드
	//데이터베이스에서 id와pw일치시 success = true 리턴
	public boolean login(Member member) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean success = false;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement("SELECT id from member where id = ? and pw = ?");
			preparedStatement.setString(1, member.getId());
			preparedStatement.setString(2, member.getPw());
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				success = true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.close(connection, preparedStatement, resultSet);
		}		
		return success;
	}
	public Member selectMember(String id) {
		return null;
	}
}
