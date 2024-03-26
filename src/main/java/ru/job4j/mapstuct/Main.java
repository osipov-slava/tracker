package ru.job4j.mapstuct;

import org.mapstruct.factory.Mappers;
import ru.job4j.mapstuct.dto.DeliveryAddressDTO;
import ru.job4j.mapstuct.dto.StudentDto;
import ru.job4j.mapstuct.mappers.DeliveryAddressMapper;
import ru.job4j.mapstuct.mappers.StudentMapper;
import ru.job4j.mapstuct.model.AddressEntity;
import ru.job4j.mapstuct.model.StudentEntity;

public class Main {
    public static void main(String[] args) {
        StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);
        StudentEntity sEntity = new StudentEntity(0, "entity", "junior");
        StudentDto sDto = new StudentDto(11, "dto", "middle");
        StudentDto fromEntity = studentMapper.getModelFromEntity(sEntity);
        System.out.println("fromEntity = " + fromEntity);
        StudentEntity fromDto = studentMapper.detEntityFromDto(sDto);
        System.out.println("fromDto = " + fromDto);

        DeliveryAddressMapper deliveryAddressMapper = Mappers.getMapper(DeliveryAddressMapper.class);
        AddressEntity address = new AddressEntity(100, "cityGood", "stateNew");
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressMapper.getDeliveryAddress(sEntity, address);
        System.out.println("deliveryAddressDTO = " + deliveryAddressDTO);
    }
}