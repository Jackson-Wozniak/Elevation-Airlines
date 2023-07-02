package github.wozniak.flighttrackingservice.repository;

import github.wozniak.flighttrackingservice.entity.Plane;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PlaneRepository extends JpaRepository<Plane, String> {

    @Query(value = "TRUNCATE table planes", nativeQuery = true)
    @Modifying
    @Transactional
    void truncatePlanes();
}
