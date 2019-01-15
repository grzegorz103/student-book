package edu.uph.ii.platformy.services.declarations;

import edu.uph.ii.platformy.models.Protocol;
import edu.uph.ii.platformy.models.ProtocolItem;
import edu.uph.ii.platformy.models.SemestralGrade;

import java.util.List;

public interface ProtocolService
{
        void addProtocols ();

        List<Protocol> getProtocols();

        void update ( ProtocolItem item );
}
