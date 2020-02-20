package com.riatServer.service.Impl;

import com.riatServer.domain.Position;
import com.riatServer.domain.Position;
import com.riatServer.exception.ServiceException;
import com.riatServer.repo.PositionsRepo;
import com.riatServer.service.EntityService;
import com.riatServer.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class PositionServiceImpl implements PositionService, EntityService<Position, Long> {
    @Autowired
    PositionsRepo positionRepo;
    
    @Override
    public List<Position> getAll() {
        return  positionRepo.findAll();
    }

    @Override
    public Position getById(Long id) {
        return positionRepo.findById(id).orElse(null);
    }

    @Override
    public Position save(Position position) {
        position.setUpdateDate(LocalDateTime.now());
        return positionRepo.save(position);
    }

    @Override
    public Position create(Position position) {
        position.setCreateDate(LocalDateTime.now());
        position.setUpdateDate(LocalDateTime.now());
        return positionRepo.save(position);
    }

    @Override
    public void delete(Long id) throws IOException, ServiceException {
        Position position = getById(id);
        positionRepo.deleteById(id);
    }
}
