package ru.spb.otus.atmDepartmnent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.spb.otus.atmDepartmnent.atm.dao.impl.BanknoteStoreImpl;
import ru.spb.otus.atmDepartmnent.atm.domain.BanknoteType;
import ru.spb.otus.atmDepartmnent.atm.service.Atm;
import ru.spb.otus.atmDepartmnent.atm.service.BanknoteAtm;
import ru.spb.otus.atmDepartmnent.atm.service.impl.AtmImpl;
import ru.spb.otus.atmDepartmnent.department.AtmDepartment;
import ru.spb.otus.atmDepartmnent.department.ListBanknoteAtm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avetall  02.05.18.
 */
@Configuration
public class Config {

    @Value("${banknoteatm.count}")
    private Integer atmCount;

    @Value("${banknoteatm.default.banknote.count}")
    private String defaultBanknote;

    @Bean
    public ListBanknoteAtm getAtms(){
        ListBanknoteAtm atms = new ListBanknoteAtm();

        for (int i = 0; i < atmCount; i++) {
            atms.add(getDefaultBanknoteAtm());
        }

        return atms;
    }

    @Bean
    public AtmDepartment getAtmDepartment(ListBanknoteAtm atms){
        AtmDepartment department = new AtmDepartment(atms);
        return department;
    }

    private BanknoteAtm getDefaultBanknoteAtm(){
        Atm atm = new AtmImpl(new BanknoteStoreImpl());
        BanknoteAtm banknoteAtm =  new BanknoteAtm(atm, BanknoteType.valueOf(defaultBanknote));
        return banknoteAtm;

    }
}
