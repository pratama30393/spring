package com.example.demo.service;

import com.example.demo.dao.NasabahDao;
import com.example.demo.dto.NasabahTransaksiDto;
import com.example.demo.dto.TabunganReport;
import com.example.demo.dto.TabunganReportDto;
import com.example.demo.model.Nasabah;
import org.apache.xmlbeans.impl.validator.Validator;
import org.apache.xmlbeans.impl.validator.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NasabahServiceImpl implements NasabahService{

    @Autowired
    private NasabahDao nasabahDao;


    @Override
    public Nasabah save(Nasabah nasabah) {
        return nasabahDao.save(nasabah);
    }

    @Override
    @Lock(LockModeType.READ)
    public List<Nasabah> findAll() {
        return nasabahDao.findAll();
    }

    @Override
    @Lock(LockModeType.READ)
    public List<NasabahTransaksiDto> findAllTransaksiNasabah() {
        return nasabahDao.listTransaksiNasabah();
    }

    @Override
    @Lock(LockModeType.READ)
    public List<TabunganReport> findTabunganByIdAndDate(Long accountId, Date start, Date end) {
        if(start.before(end) || start == null ||end.after(end) || end == null ){
            return null;
        }
        return nasabahDao.listTabungan(accountId, start, end);
    }

/*@Override
    public List<NasabahTransaksiDto> findtransaksiNasabahById(Long accountId) {
        return nasabahDao.findAllTransaksiNasabahById(accountId);
    }*/
}
