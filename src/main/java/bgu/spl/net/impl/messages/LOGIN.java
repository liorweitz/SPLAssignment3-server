package bgu.spl.net.impl.messages;

import bgu.spl.net.impl.BGRSServer.ConnectionHandler;
import bgu.spl.net.impl.BGRSServer.Database;
import bgu.spl.net.impl.BGRSServer.Message;
import java.util.Scanner;

public class LOGIN implements Message {
    final int opcode=3;
    private String userName;
    private String pwd;

    public LOGIN(String substring) {
        Scanner sc=new Scanner(substring);
        sc.useDelimiter("\0");
        userName=sc.next();
        pwd=sc.next();
    }

    @Override
    public Message process(ConnectionHandler handler) {
        Database db = Database.getInstance();
        if (db.isLoggedIn(handler)==-1) {
            if (db.isAdminExist(userName)) {
                if (pwd.equals(db.getAdminPwd(userName))) {
                    db.logIn(handler, userName);
                    return new ACK(opcode);
                }
            } else if (db.isStudentExist(userName)) {
                if (pwd.equals(db.getStudentPwd(userName))) {
                    db.logIn(handler, userName);
                    return new ACK(opcode);
                }
            }
        }
        return new ERR(opcode);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }
}
