package com.barisd;

import com.barisd.repository.DepartmentRepository;
import com.barisd.repository.PersonelRepository;
import com.barisd.repository.entity.Department;
import com.barisd.repository.entity.Personel;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Department oluşturup,kaydedelim.,
        DepartmentRepository departmentRepository=new DepartmentRepository();

//        Department departmentSatis= Department.builder()
//                .name("Satış Departmanı")
//                .build();
//
//        Department departmentMuhasabe= Department.builder()
//                .name("Muhasabe Departmanı")
//                .build();

//        departmentRepository.save(departmentSatis);
//        departmentRepository.save(departmentMuhasabe);


        //Personelleri oluşturup, departmanlarını da verip, kaydedelim.
        PersonelRepository personelRepository=new PersonelRepository();
//        Personel p1= Personel.builder()
//                .name("HAKAN")
//                .age(35)
//                .department(departmentSatis)
//                .build();
//
//        Personel p2= Personel.builder()
//                .name("CAN")
//                .age(30)
//                .department(departmentSatis)
//                .build();
//
//        Personel p3= Personel.builder()
//                .name("BİLGE")
//                .age(40)
//                .department(departmentMuhasabe)
//                .build();
//
//        personelRepository.saveAll(List.of(p1,p2,p3));

       // departmentRepository.deleteById(1L);
        personelRepository.deleteById(3L);
        //önce personel tarafından sorgu atalım:
//        personelRepository.findById(2L).ifPresent(
//                p -> {
//                    System.out.println(p.getName());
//                    System.out.println(p.getDepartment().getName());
//                }
//        );

      //  personelRepository.findAll().forEach(System.out::println);

        //departmentRepository.findAll().forEach(System.out::println);

    }
}