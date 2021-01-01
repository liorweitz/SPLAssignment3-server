package bgu.spl.net.impl.messages;

import bgu.spl.net.srv.ConnectionHandler;
import bgu.spl.net.srv.Database;
import bgu.spl.net.srv.Message;
import bgu.spl.net.srv.MessageWithProcess;

import java.net.SocketAddress;

public class LOGOUT implements MessageWithProcess {
    final int opcode=4;
    public LOGOUT(String substring) {
    }

    @Override
    public Message process(ConnectionHandler handler) {
        Database db=Database.getInstance();

        if (db.isLoggedIn(handler)!=-1){
            System.out.println("logged out");
            db.logOut(handler);
            return new ACK(opcode);
        }
        else{
            System.out.println("handler isnt logged in");
            return new ERR(opcode);

        }

    }
}