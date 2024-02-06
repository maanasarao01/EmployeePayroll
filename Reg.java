
import java.rmi.*;


public class Reg {
    public static void main(String a[]) throws RemoteException {
        try{

            Menu e=new Emp();
           java.rmi.registry.LocateRegistry.createRegistry(3308);
            Naming.rebind("//:3308/server",e);
            System.out.println("Registry created");
        }
        catch (Exception e1){
            System.out.println("Registry could not be created\n "+e1);
        }
    }
}
