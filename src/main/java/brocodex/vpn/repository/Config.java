package brocodex.vpn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Config extends JpaRepository<Config, Long> {
}