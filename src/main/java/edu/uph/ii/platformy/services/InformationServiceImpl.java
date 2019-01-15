package edu.uph.ii.platformy.services;


import edu.uph.ii.platformy.models.Information;
import edu.uph.ii.platformy.repositories.InformationRepository;
import edu.uph.ii.platformy.services.declarations.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service ("informationService")
public class InformationServiceImpl implements InformationService
{
        private final InformationRepository informationRepository;


        @Autowired
        public InformationServiceImpl ( InformationRepository informationRepository )
        {
                this.informationRepository = informationRepository;
        }


        @Override
        public Page<Information> getAll ( Pageable pageable )
        {
                return informationRepository.findAll( pageable );
        }


        @Override
        public void add ( Information information )
        {
                informationRepository.saveAndFlush( information );
        }


        @Override
        public Information getById ( Long id )
        {
                return informationRepository.findById( id ).get();
        }
}
