
import java.rmi.*;
import java.rmi.server.*;
import  java.sql.*;

public class Emp extends UnicastRemoteObject implements Menu {
    int eno;
    String ename;
    float basic, da, hra, net;
    Conn c=new Conn();

    Emp() throws RemoteException {

    }

    ResultSet rs;

    public void setEmp(int eno, String ename, float basic) throws RemoteException, SQLException {
        this.eno = eno;
        this.ename = ename;
        this.basic = basic;
        calculate();
    }

    void calculate() throws RemoteException, SQLException {
        da = basic * 0.2f;
        hra = basic * 0.5f;
        net = basic + da + hra;
        writeEmp(eno, ename, basic, da, hra, net);
    }

    public float getDa() throws RemoteException{
        return da;
    }
   public float getHra() throws RemoteException{
        return hra;
    }
    public float getNet() throws RemoteException{
        return net;
    }
    public int getEmp(int eno) throws RemoteException, SQLException {

        if ((rs = readEmp(eno)).next()) {
            this.eno = rs.getInt(1);
            ename = rs.getString(2);
            basic = rs.getFloat(3);
            da = rs.getFloat(4);
            hra = rs.getFloat(5);
            net = rs.getFloat(6);
            return 1;
        } else
            return 0;
    }

    public void writeEmp(int eno, String ename, float basic, float da, float hra, float net) throws RemoteException, SQLException {

        c.s.executeUpdate("insert into emp values(eno,ename,basic,da,hra,net)");
        c.con.commit();
    }

    public ResultSet readEmp(int empno) throws RemoteException, SQLException {
        rs = c.s.executeQuery("select * from emp where eno=empno");
        return rs;
    }

    public int dltEmp(int empno) throws SQLException, RemoteException {
        int cnt = c.s.executeUpdate("delete from emp where eno=empno");
        return cnt;
    }


    public static void main(String a[]) throws RemoteException, SQLException {

        Emp obj = new Emp();

    }
}
