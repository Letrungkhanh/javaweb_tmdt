package dal;

import models.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class AccountDAO extends DBContext {

    public Account login(String username, String password) {
        String sql = """
            SELECT a.*, r.RoleName
            FROM tb_Account a
            JOIN tb_Role r ON a.RoleId = r.RoleId
            WHERE a.Username = ? AND a.Password = ? AND a.IsActive = 1
        """;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setAccountId(rs.getInt("AccountId"));
                acc.setUsername(rs.getString("Username"));
                acc.setFullName(rs.getString("FullName"));
                acc.setPhone(rs.getString("Phone"));
                acc.setEmail(rs.getString("Email"));
                acc.setRoleId(rs.getInt("RoleId"));
                acc.setRoleName(rs.getString("RoleName"));
                acc.setActive(rs.getBoolean("IsActive"));
                acc.setCreatedDate(rs.getTimestamp("CreatedDate"));
                acc.setLastLogin(rs.getTimestamp("LastLogin"));

                return acc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // update last login
    public void updateLastLogin(int accountId) {
        String sql = "UPDATE tb_Account SET LastLogin = NOW() WHERE AccountId=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Account> getAllCustomers() {
        List<Account> list = new ArrayList<>();

        String sql = """
            SELECT a.*, r.RoleName
            FROM tb_Account a
            JOIN tb_Role r ON a.RoleId = r.RoleId
            WHERE r.RoleName = 'Customer'
            ORDER BY a.CreatedDate DESC
        """;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Account acc = new Account();
                acc.setAccountId(rs.getInt("AccountId"));
                acc.setUsername(rs.getString("Username"));
                acc.setFullName(rs.getString("FullName"));
                acc.setPhone(rs.getString("Phone"));
                acc.setEmail(rs.getString("Email"));
                acc.setRoleName(rs.getString("RoleName"));
                acc.setActive(rs.getBoolean("IsActive"));
                acc.setCreatedDate(rs.getTimestamp("CreatedDate"));

                list.add(acc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
 // khóa / mở tài khoản
    public void updateAccountStatus(int accountId, boolean isActive) {
        String sql = "UPDATE tb_Account SET IsActive = ? WHERE AccountId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, isActive);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkOldPassword(int accountId, String oldPassword) {
        String sql = "SELECT 1 FROM tb_Account WHERE AccountId = ? AND Password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setString(2, oldPassword);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updatePassword(int accountId, String newPassword) {
        String sql = "UPDATE tb_Account SET Password = ? WHERE AccountId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
