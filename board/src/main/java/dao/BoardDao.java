package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDto;

public class BoardDao {
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

    // 3. CRUD
    // 전체 리스트 가져오기
    public List<BoardDto> getList() {
        List<BoardDto> list = new ArrayList<>();
        con = getConnection();
        String sql = "SELECT bno, title, NAME , REGDATE , READ_COUNT , re_seq, RE_LEV FROM BOARD ORDER BY RE_ref DESC, re_seq asc";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BoardDto dto = new BoardDto();

                dto.setBno(rs.getInt("bno"));
                dto.setTitle(rs.getString("title"));
                dto.setName(rs.getString("name"));
                dto.setRegDate(rs.getDate("regdate"));
                dto.setReadCount(rs.getInt("read_count"));
                dto.setReLev(rs.getInt("re_lev"));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return list;
    }

    // 새 글 작성
    public int create(BoardDto insertDto) {
        int result = 0;
        con = getConnection();
        String sql = "INSERT INTO BOARD (bno, name, password, title, content, attach, re_ref, re_lev, re_seq) ";
        sql += "values(board_seq.nextval, ?, ?, ?, ?, ?,board_seq.currval, ?, ?)";
        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, insertDto.getName());
            pstmt.setString(2, insertDto.getPassword());
            pstmt.setString(3, insertDto.getTitle());
            pstmt.setString(4, insertDto.getContent());
            pstmt.setString(5, insertDto.getAttach());
            pstmt.setInt(6, 0);
            pstmt.setInt(7, 0);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // 개별 글 조회
    public BoardDto getRow(int bno) {
        BoardDto dto = null;

        try {
            con = getConnection();
            String sql = "select bno, name, title, content, attach, re_ref, re_seq, re_lev from board where bno = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bno);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto = new BoardDto();
                dto.setBno(rs.getInt("bno"));
                dto.setName(rs.getString("name"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setAttach(rs.getString("attach"));
                // reply 에 필요함
                dto.setReRef(rs.getInt("re_ref"));
                dto.setReSeq(rs.getInt("re_seq"));
                dto.setReLev(rs.getInt("re_lev"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return dto;
    }

    // 특정 글 수정
    public int update(BoardDto updateDto) {
        // bno 와 password 일치 시 제목, 내용 수정
        int result = 0;
        con = getConnection();
        String sql = "update board set title = ? , content = ? where bno = ? and password = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, updateDto.getTitle());
            pstmt.setString(2, updateDto.getContent());
            pstmt.setInt(3, updateDto.getBno());
            pstmt.setString(4, updateDto.getPassword());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // 특정 글 삭제
    public int delete(BoardDto deleteDto) {
        int result = 0;
        con = getConnection();
        String sql = "delete from board where bno = ? and password = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, deleteDto.getBno());
            pstmt.setString(2, deleteDto.getPassword());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // 댓글 작성
    public int reply(BoardDto replyDto) {
        int result = 0;
        con = getConnection();

        int reRef = replyDto.getReRef();
        int reSeq = replyDto.getReSeq();
        int reLev = replyDto.getReLev();

        try {
            String sql = "update board set RE_SEQ = RE_SEQ + 1 WHERE RE_REF = ? AND re_seq > ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, reRef);
            pstmt.setInt(2, reSeq);
            pstmt.executeUpdate();

            sql = "INSERT INTO board(bno, name, PASSWORD,title, CONTENT, re_ref, RE_LEV, RE_SEQ) ";
            sql += "values(board_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, replyDto.getName());
            pstmt.setString(2, replyDto.getPassword());
            pstmt.setString(3, replyDto.getTitle());
            pstmt.setString(4, replyDto.getContent());
            pstmt.setInt(5, reRef);
            pstmt.setInt(6, reLev + 1);
            pstmt.setInt(7, reSeq + 1);

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
