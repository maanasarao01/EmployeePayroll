import java.rmi.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Menu extends Remote {
    void setEmp(int eno,String ename,float basic) throws RemoteException,SQLException;
    int getEmp(int eno) throws RemoteException,SQLException;
    void writeEmp(int eno, String ename, float basic, float da, float hra, float net) throws RemoteException,SQLException;
    float getDa() throws RemoteException;
    float getHra() throws RemoteException;
    float getNet() throws RemoteException;
    ResultSet readEmp(int empno) throws RemoteException, SQLException;
    int dltEmp(int empno) throws SQLException,RemoteException;
}
