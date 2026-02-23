package vitor.decisaoimobiliaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vitor.decisaoimobiliaria.entity.AisStatistics;

import java.util.List;

public interface AisStatisticsRepository extends JpaRepository<AisStatistics, Integer> {
    List<AisStatistics> findByAis(Integer ais);
}
