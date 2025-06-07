package admin.DAO;

public interface AdminSQL {
    /**
     * 以下admin表的操作
     * */
    //查询重复信息
    String SQL_FIND_ADMIN = "SELECT * FROM admin WHERE name = ? AND password = ?";

    //插入全部信息
    String SQL_INSER_ALL_ADMIN = "INSERT INTO admin (name, password, phone, workid) VALUES (?, ? ,? ,?)";

    //根据管理员id更新该管理员全部数据
    String SQL_UPDATE_Admin = "UPDATE admin SET name = ?, password = ?, phone = ? workid = ? WHERE id = ?";
}
