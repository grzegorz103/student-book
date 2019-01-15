package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Scholarship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScholarshipService
{
    Page< Scholarship > findScholarships ( Pageable pageable );
}
