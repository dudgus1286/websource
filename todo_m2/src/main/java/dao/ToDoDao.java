package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.ToDoDto;

public class ToDoDao {
    // JDBD 단계
    // 1. 드라이버 로드
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 2. 커넥션 얻기
    public Connection getConnection() {
        // 커넥션 풀 방식
        Context initContext;
        try {
            initContext = new InitialContext();
            // 등록된 이름들을 관리하는 곳으로 가라고 명령 - "java:/comp/env"
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            // 그곳에서 이름으로 지정된 저장소를 찾기 - "jdbc/myoracle"
            DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
            con = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    // 3. sql 작업 = CRUD 메소드 구현
    // 전체 조회 - Read
    public List<ToDoDto> getList() {
        List<ToDoDto> list = new ArrayList<>();
        con = getConnection();

        String sql = "select no, title, created_at, completed from todotbl order by no desc";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ToDoDto dto = new ToDoDto();

                dto.setNo(rs.getInt("no"));
                dto.setTitle(rs.getString("title"));
                dto.setCreatedAt(rs.getDate("created_at"));
                dto.setCompleted(rs.getBoolean("completed"));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return list;
    }

    // 개별로 조회
    public ToDoDto getRow(String no) {
        ToDoDto dto = null;
        con = getConnection();

        String sql = "select * from todotbl where no=?";
        try {
            pstmt = con.prepareStatement(sql);
            // ? 해결
            pstmt.setInt(1, Integer.parseInt(no));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dto = new ToDoDto();
                dto.setNo(rs.getInt("no"));
                dto.setTitle(rs.getString("title"));
                dto.setCreatedAt(rs.getDate("created_at"));
                dto.setCompleted(rs.getBoolean("completed"));
                dto.setDescription(rs.getString("description"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return dto;
    }

    // 추가 - create
    public int insert(ToDoDto insertDto) {
        int result = 0;
        con = getConnection();

        String sql = "INSERT INTO TODOTBL(NO, title, description) values(todo_seq.nextval, ?, ?)";

        try {
            pstmt = con.prepareStatement(sql);
            // ? 해결
            pstmt.setString(1, insertDto.getTitle());
            pstmt.setString(2, insertDto.getDescription());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // 수정 - update
    public int update(ToDoDto insertDto) {
        int result = 0;
        con = getConnection();

        String sql = "UPDATE TODOTBL SET completed=?, DESCRIPTION =? WHERE NO = ?";

        try {
            pstmt = con.prepareStatement(sql);
            // ? 해결
            pstmt.setBoolean(1, insertDto.isCompleted());
            pstmt.setString(2, insertDto.getDescription());
            pstmt.setInt(3, insertDto.getNo());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // 삭제 - delete
    public int delete(String no) {
        int result = 0;
        con = getConnection();

        String sql = "DELETE TODOTBL WHERE NO = ?";

        try {
            pstmt = con.prepareStatement(sql);
            // ? 해결
            pstmt.setInt(1, Integer.parseInt(no));
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // 4. 자원 정리
    public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (con != null)
                con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close(Connection con, PreparedStatement pstmt) {
        try {
            if (pstmt != null)
                pstmt.close();
            if (con != null)
                con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
