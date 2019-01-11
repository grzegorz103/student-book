package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Message;

import java.util.List;

public interface MessageService
{
        void send ( Message message );

        List<Message> getMessages ();
}
