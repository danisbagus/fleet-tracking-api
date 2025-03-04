package danisbagus.fleet_tracking_api.repository;

import danisbagus.fleet_tracking_api.domain.entity.FleetEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class FleetJdbcRepository {
    private final JdbcTemplate springJdbcTemplate;

    public FleetJdbcRepository(JdbcTemplate springJdbcTemplate) {
        this.springJdbcTemplate = springJdbcTemplate;
    }

    public List<FleetEntity> findAll() {
        String sql = "SELECT * FROM fleet";

        return springJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FleetEntity.class));
    }

    public Integer insert(FleetEntity fleet) {
        String sql = "insert into fleet (vehicle_number, vehicle_type, created_at, updated_at) values(?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        springJdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, fleet.getVehicleNumber());
            ps.setString(2, fleet.getVehicleType().toString());
            ps.setTimestamp(3, fleet.getCreatedAt());
            ps.setTimestamp(4, fleet.getUpdatedAt());
            return ps;
        }, keyHolder);

        // get id value from key holder (H2 not support RETURNING query)
        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null && keys.containsKey("id")) {
            return ((Number) keys.get("id")).intValue();
        }

        return -1; // if id not found
    }

    public Optional<FleetEntity> findByID(Integer id) {
        String sql = "select * from fleet where id = ?";

        try {
            FleetEntity fleet = springJdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(FleetEntity.class),
                    id);
            return Optional.ofNullable(fleet);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Boolean deleteByID(Integer id) {
        String sql = "delete from fleet where id = ?";

        int rowsAffected = springJdbcTemplate.update(sql, id);

        return rowsAffected > 0;
    }

    public Boolean updateByID(Integer id, FleetEntity payload) {
        String sql = "update fleet set vehicle_number = ?, vehicle_type = ?, updated_at = ? where id = ?";

        int rowsAffected = springJdbcTemplate.update(sql,
                payload.getVehicleNumber(),
                payload.getVehicleType().toString(),
                payload.getUpdatedAt(),
                id);

        return rowsAffected > 0;
    }
}
