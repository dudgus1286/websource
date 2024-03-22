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

import dto.BookDto;

public class BookDao {
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

        // 기존 방식
        // webapp/META-INF/context.xml 에 작성한 내용임
        // String url = "jdbc:oracle:thin:@localhost:1521:xe";
        // String user = "c##test2";
        // String password = "test";

        // try {
        // con = DriverManager.getConnection(url, user, password);
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        // return con;
    }

    // 3. CRUD
    // R - 전체 조회
    public List<BookDto> getList() {
        List<BookDto> list = new ArrayList<>();
        con = getConnection();
        String sql = "Select * from BookTBL order by code desc";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookDto dto = new BookDto();

                dto.setCode(rs.getInt("code"));
                dto.setTitle(rs.getString("title"));
                dto.setWriter(rs.getString("writer"));
                dto.setPrice(rs.getInt("price"));
                dto.setDescription(rs.getString("description"));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return list;
    }

    // R - 개별 조회
    public BookDto getrow(int code) {
        BookDto dto = new BookDto();
        con = getConnection();

        String sql = "Select * from BookTBL where code=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, code);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                dto.setCode(rs.getInt("code"));
                dto.setTitle(rs.getString("title"));
                dto.setWriter(rs.getString("writer"));
                dto.setPrice(rs.getInt("price"));
                dto.setDescription(rs.getString("description"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return dto;
    }

    // U - 수정
    public int update(BookDto updateDto) {
        int result = 0;
        con = getConnection();
        String sql = "update booktbl set price = ? where code = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, updateDto.getPrice());
            pstmt.setInt(2, updateDto.getCode());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // C - 추가
    public int insert(BookDto insertDto) {

        int result = 0;
        con = getConnection();

        String sql = "Insert into BookTBL(code, title, writer, price, description) values( ? , ? , ? , ? , ? )";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, insertDto.getCode());
            pstmt.setString(2, insertDto.getTitle());
            pstmt.setString(3, insertDto.getWriter());
            pstmt.setInt(4, insertDto.getPrice());
            pstmt.setString(5, insertDto.getDescription());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // R - 조건 검색
    public List<BookDto> searchList(String criteria, String keyword) {
        List<BookDto> list = new ArrayList<>();
        con = getConnection();
        String sql = "select * from booktbl where ";
        // 검색 기준이 code라면 code = ? , writer 라면 writer = ?
        if (criteria.equals("code")) {
            sql += " code = ?";
        } else {
            sql += " writer = ?";
        }
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, keyword);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookDto dto = new BookDto();

                dto.setCode(rs.getInt("code"));
                dto.setTitle(rs.getString("title"));
                dto.setWriter(rs.getString("writer"));
                dto.setPrice(rs.getInt("price"));
                dto.setDescription(rs.getString("description"));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return list;
    }

    // D - 제거
    public int delete(int code) {
        int result = 0;
        con = getConnection();
        String sql = "delete booktbl where code = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, code);
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
