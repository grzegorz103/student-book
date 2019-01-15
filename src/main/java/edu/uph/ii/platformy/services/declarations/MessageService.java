package edu.uph.ii.platformy.services.declarations;

import edu.uph.ii.platformy.models.Message;

import java.util.List;

public interface MessageService
{
        void send ( Message message );

        List<Message> getMessages ();

        List<Message> getUserMessages();
}
