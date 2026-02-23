package vitor.decisaoimobiliaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vitor.decisaoimobiliaria.entity.BairroAis;

import java.util.List;

public interface BairroAisRepository extends JpaRepository<BairroAis, String> {

    List<BairroAis> findByAis(Integer ais);
}
