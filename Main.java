
import Model.Department;
import Model.Employee;
import Model.Firm;
import Model.Module;
import Model.Project;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.*;
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Selen
 */
@ManagedBean
@RequestScoped
public class Main implements Filter {

    static Firm firm = new Firm();
    static Department department = new Department();
    static Employee employee = new Employee();
    static Project project = new Project();
    static Module module = new Module();
    int i = 0;

    public Main() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void send() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("SET IDENTITY_INSERT FIRM ON INSERT INTO FIRM(firmID,firmName,firmAddress,phoneNumber,faxNumber,eMail) VALUES(?,?,?,?,?,?) SET IDENTITY_INSERT FIRM OFF");
                ps.setInt(1, firm.getFirmId());
                ps.setString(2, firm.getFirmName());
                ps.setString(3, firm.getFirmAddress());
                ps.setString(4, firm.getPhoneNumber());
                ps.setString(5, firm.getFaxNumber());
                ps.setString(6, firm.getEmail());

                i = ps.executeUpdate();
            }

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public void update() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("UPDATE FIRM SET firmName=?,firmAddress=?,phoneNumber=?,faxNumber=?,eMail=? WHERE firmID=?");

                ps.setString(1, firm.getFirmName());
                ps.setString(2, firm.getFirmAddress());
                ps.setString(3, firm.getPhoneNumber());
                ps.setString(4, firm.getFaxNumber());
                ps.setString(5, firm.getEmail());
                ps.setInt(6, firm.getFirmId());
                i = ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public void delete() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("DELETE FROM FIRM WHERE firmID=?");
                ps.setInt(1, firm.getFirmId());
                i = ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public void sendDep() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("SET IDENTITY_INSERT DEPARTMENT ON INSERT INTO DEPARTMENT(departmentID,departmentName,departmentMail,firmID,departmentManagerID,phoneNumber) VALUES(?,?,?,?,?,?) SET IDENTITY_INSERT DEPARTMENT OFF");
                ps.setInt(1, department.getDepId());
                ps.setString(2, department.getDepName());
                ps.setString(3, department.getDepMail());
                ps.setInt(4, department.getFirmId());
                ps.setBigDecimal(5, department.getManagerId());
                ps.setString(6, department.getPhoneNumber());

                i = ps.executeUpdate();
            }

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public void updateDep() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("UPDATE DEPARTMENT SET departmentName=?,departmentMail=?,firmID=?,departmentManagerID=?,phoneNumber=? WHERE departmentID=?");

                ps.setString(1, department.getDepName());
                ps.setString(2, department.getDepMail());
                ps.setInt(3, department.getFirmId());
                ps.setBigDecimal(4, department.getManagerId());
                ps.setString(5, department.getPhoneNumber());
                ps.setInt(6, department.getDepId());
                i = ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public void deleteDep() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("DELETE FROM DEPARTMENT WHERE departmentID=?");
                ps.setInt(1, department.getDepId());
                i = ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public void sendEmp() {
        Connection conn = null;
        PreparedStatement ps = null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("SET IDENTITY_INSERT EMPLOYEE ON INSERT INTO EMPLOYEE(SSN,employeeName,employeeAddress,phoneNumber,eMail,departmentID,startTime,officeID,workingTime,extraMoney) VALUES(?,?,?,?,?,?,?,?,?,?) SET IDENTITY_INSERT EMPLOYEE OFF");

                ps.setBigDecimal(1, employee.getSsn());
                ps.setString(2, employee.getEmpName());
                ps.setString(3, employee.getEmpAddr());
                ps.setString(4, employee.getPhoneNumber());
                ps.setString(5, employee.getEmail());
                ps.setInt(6, employee.getDepId());
                if (employee.getStartTime() != null) {
                    String date = fmt.format(employee.getStartTime());
                    Object obj = date;
                    if (obj == null) {
                        ps.setDate(7, null);
                    } else {
                        java.sql.Date dt = java.sql.Date.valueOf(new String(date));
                        ps.setDate(7, dt);
                    }

                }
                ps.setInt(8, employee.getOfficeId());
                ps.setInt(9, 0);
                ps.setInt(10, 0);
                i = ps.executeUpdate();

            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public void updateEmp() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("UPDATE DEPARTMENT SET departmentName=?,departmentMail=?,firmID=?,departmentManagerID=?,phoneNumber=? WHERE departmentID=?");

                ps.setString(1, department.getDepName());
                ps.setString(2, department.getDepMail());
                ps.setInt(3, department.getFirmId());
                ps.setBigDecimal(4, department.getManagerId());
                ps.setString(5, department.getPhoneNumber());
                ps.setInt(6, department.getDepId());
                i = ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public void deleteEmp() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("DELETE FROM DEPARTMENT WHERE departmentID=?");
                ps.setInt(1, department.getDepId());
                i = ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public void sendPro() {
        Connection conn = null;
        PreparedStatement ps = null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("SET IDENTITY_INSERT PROJECT ON INSERT INTO PROJECT(projectID,projectName,importanceID,startDate,dueDate,projectManagerID,departmentID,explanation,timeLeft) VALUES(?,?,?,?,?,?,?,?,?) SET IDENTITY_INSERT PROJECT OFF");
                ps.setInt(1, project.getProjectId());
                ps.setString(2, project.getProName());
                ps.setInt(3, project.getImpId());
                if (project.getStartDate() != null) {
                    String date = fmt.format(project.getStartDate());
                    Object obj = date;
                    if (obj == null) {
                        ps.setDate(4, null);
                    } else {
                        java.sql.Date dt = java.sql.Date.valueOf(new String(date));
                        ps.setDate(4, dt);
                    }

                }
                if (project.getDueDate() != null) {
                    String date = fmt.format(project.getDueDate());
                    Object obj = date;
                    if (obj == null) {
                        ps.setDate(5, null);
                    } else {
                        java.sql.Date dt = java.sql.Date.valueOf(new String(date));
                        ps.setDate(5, dt);
                    }

                }
                ps.setBigDecimal(6, project.getManagerId());
                ps.setInt(7, project.getDeptID());
                ps.setString(8, project.getExp());
                ps.setInt(9, 0);

                i = ps.executeUpdate();
            }

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public void updatePro() {
        Connection conn = null;
        PreparedStatement ps = null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("UPDATE PROJECT SET projectName=?,importanceID=?,startDate=?,dueDate=?,projectManagerID=?,departmentID=?,explanation=?,timeLeft=? WHERE projectID=?");

                ps.setString(1, project.getProName());
                ps.setInt(2, project.getImpId());
                if (project.getStartDate() != null) {
                    String date = fmt.format(project.getStartDate());
                    Object obj = date;
                    if (obj == null) {
                        ps.setDate(3, null);
                    } else {
                        java.sql.Date dt = java.sql.Date.valueOf(new String(date));
                        ps.setDate(3, dt);
                    }

                }
                if (project.getDueDate() != null) {
                    String date = fmt.format(project.getDueDate());
                    Object obj = date;
                    if (obj == null) {
                        ps.setDate(4, null);
                    } else {
                        java.sql.Date dt = java.sql.Date.valueOf(new String(date));
                        ps.setDate(4, dt);
                    }

                }
                ps.setBigDecimal(5, project.getManagerId());
                ps.setInt(6, project.getDeptID());
                ps.setString(7, project.getExp());
                ps.setInt(8, 0);
                ps.setInt(9, project.getProjectId());

                i = ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public void deletePro() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("DELETE FROM PROJECT WHERE projectID=?");
                ps.setInt(1, project.getProjectId());
                i = ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public void sendMod() {
        Connection conn = null;
        PreparedStatement ps = null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("SET IDENTITY_INSERT MODULE ON INSERT INTO MODULE(moduleID,moduleName,startDate,dueDate,statusID,explanation,moduleManagerID,projectID) VALUES(?,?,?,?,?,?,?,?) SET IDENTITY_INSERT MODULE OFF");
                ps.setInt(1, module.getModId());
                ps.setString(2, module.getModName());
                if (module.getStartDate() != null) {
                    String date = fmt.format(module.getStartDate());
                    Object obj = date;
                    if (obj == null) {
                        ps.setDate(3, null);
                    } else {
                        java.sql.Date dt = java.sql.Date.valueOf(new String(date));
                        ps.setDate(3, dt);
                    }

                }
                if (module.getDueDate() != null) {
                    String date = fmt.format(module.getDueDate());
                    Object obj = date;
                    if (obj == null) {
                        ps.setDate(4, null);
                    } else {
                        java.sql.Date dt = java.sql.Date.valueOf(new String(date));
                        ps.setDate(4, dt);
                    }

                }
                ps.setInt(5, module.getStatusId());
                ps.setString(6, module.getExp());
                ps.setBigDecimal(7, module.getManagerId());
                ps.setInt(8, module.getProId());

                i = ps.executeUpdate();
            }

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public void updateMod() {
        Connection conn = null;
        PreparedStatement ps = null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("UPDATE MODULE SET moduleName = ?,startDate=?,dueDate=?,statusID=?,explanation=?,moduleManagerID=?,projectID=? WHERE moduleID=?");

                ps.setString(1, module.getModName());
                if (module.getStartDate() != null) {
                    String date = fmt.format(module.getStartDate());
                    Object obj = date;
                    if (obj == null) {
                        ps.setDate(2, null);
                    } else {
                        java.sql.Date dt = java.sql.Date.valueOf(new String(date));
                        ps.setDate(2, dt);
                    }

                }
                if (module.getDueDate() != null) {
                    String date = fmt.format(module.getDueDate());
                    Object obj = date;
                    if (obj == null) {
                        ps.setDate(3, null);
                    } else {
                        java.sql.Date dt = java.sql.Date.valueOf(new String(date));
                        ps.setDate(3, dt);
                    }

                }
                ps.setInt(4, module.getStatusId());
                ps.setString(5, module.getExp());
                ps.setBigDecimal(6, module.getManagerId());
                ps.setInt(7, module.getProId());
                ps.setInt(8, module.getModId());

                i = ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public void deleteMod() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            if (conn != null) {
                ps = conn.prepareStatement("DELETE FROM MODULE WHERE moduleID=?");
                ps.setInt(1, module.getModId());
                i = ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (i > 0) {
            System.out.println("Başarılı");
        } else {
            System.out.println("Başarısız");
        }

    }

    public List<Project> getProList() {
        List<Project> list = new ArrayList<Project>();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            String sql = "select * from PROJECT";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Project pro = new Project();
                pro.setProjectId(rs.getInt("projectID"));
                pro.setProName(rs.getString("projectName"));
                pro.setDeptID(rs.getInt("departmentID"));
                pro.setDueDate(null);
                pro.setExp(rs.getString("explanation"));
                pro.setImpId(rs.getInt("importanceID"));
                pro.setStartDate(null);
                pro.setManagerId(rs.getBigDecimal("projectManagerID"));

                list.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Module> getModList() {
        List<Module> list = new ArrayList<Module>();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            String sql = "select * from MODULE";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Module mod = new Module();
                mod.setDueDate(null);
                mod.setExp(rs.getString("explanation"));
                mod.setManagerId(rs.getBigDecimal("moduleManagerID"));
                mod.setModId(rs.getInt("moduleID"));
                mod.setModName(rs.getString("moduleName"));
                mod.setProId(rs.getInt("projectID"));
                mod.setStartDate(null);
                mod.setStatusId(rs.getInt("statusID"));

                list.add(mod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Project> getDepList() {
        List<Project> list = new ArrayList<Project>();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            String sql = "select * from PROJECT";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Project pro = new Project();
                pro.setProjectId(rs.getInt("projectID"));
                pro.setProName(rs.getString("projectName"));
                pro.setDeptID(rs.getInt("departmentID"));
                pro.setDueDate(null);
                pro.setExp(rs.getString("explanation"));
                pro.setImpId(rs.getInt("importanceID"));
                pro.setStartDate(null);
                pro.setManagerId(rs.getBigDecimal("projectManagerID"));

                list.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Project> getFirmList() {
        List<Project> list = new ArrayList<Project>();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROACTIVE";
            conn = DriverManager.getConnection(dbURL, "sa", "1234");
            String sql = "select * from PROJECT";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Project pro = new Project();
                pro.setProjectId(rs.getInt("projectID"));
                pro.setProName(rs.getString("projectName"));
                pro.setDeptID(rs.getInt("departmentID"));
                pro.setDueDate(null);
                pro.setExp(rs.getString("explanation"));
                pro.setImpId(rs.getInt("importanceID"));
                pro.setStartDate(null);
                pro.setManagerId(rs.getBigDecimal("projectManagerID"));

                list.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

}
