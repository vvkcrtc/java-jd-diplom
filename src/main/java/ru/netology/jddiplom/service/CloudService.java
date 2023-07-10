package ru.netology.jddiplom.service;

import org.springframework.stereotype.Service;
import ru.netology.jddiplom.repository.UserData;
import ru.netology.jddiplom.repository.UsersRepository;

import java.util.List;


@Service
public class CloudService {

    private final UsersRepository usersRepository;

    public CloudService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<UserData> getAll() {
        return usersRepository.getAll();
    }


}


