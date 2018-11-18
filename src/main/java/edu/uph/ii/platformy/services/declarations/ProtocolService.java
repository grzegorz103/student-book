package edu.uph.ii.platformy.services.declarations;

import edu.uph.ii.platformy.models.Error;
import edu.uph.ii.platformy.models.Protocol;
import edu.uph.ii.platformy.models.ProtocolItem;
import edu.uph.ii.platformy.models.Subject;

import java.util.List;

public interface ProtocolService
{
        void addProtocols ();

        List<Protocol> getProtocols ();

        void update ( ProtocolItem item );

        void addError ( Error error );

        List<Protocol> getAll ();

        List<Subject> getAwaitingSubjectsForProtocol ();

        void openAll ();

        void editStateByProtocol ( Protocol protocol );

        List<Error> getAllErrors ();
}
