package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Information;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InformationService
{
        Page<Information> getAll ( Pageable pageable );

        void add ( Information information );

        Information getById ( Long id );
}
