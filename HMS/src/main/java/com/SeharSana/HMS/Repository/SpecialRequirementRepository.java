package com.SeharSana.HMS.Repository;

import com.SeharSana.HMS.entity.SpecialRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialRequirementRepository extends JpaRepository<SpecialRequirement,Long>
{
    List<SpecialRequirement> findByGuestId(long guestId);
}
