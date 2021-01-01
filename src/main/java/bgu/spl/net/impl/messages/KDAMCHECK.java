package bgu.spl.net.impl.messages;

import bgu.spl.net.srv.ConnectionHandler;
import bgu.spl.net.srv.Database;
import bgu.spl.net.srv.Message;
import bgu.spl.net.srv.MessageWithProcess;

import java.net.SocketAddress;

public class KDAMCHECK implements MessageWithProcess {
    final int opcode=6;
    private int courseNum;

    public KDAMCHECK(String substring) {
        this.courseNum=Integer.parseInt(substring);
    }

    @Override
    public Message process(ConnectionHandler handler) {
        Database db=Database.getInstance();
        if (!db.checkCourseExistance(courseNum))
            return new ERR(opcode);
        return new Data(db.getKdam(courseNum).toString());
    }
}