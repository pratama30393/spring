package com.example.demo.service;

        import com.example.demo.dao.NasabahDao;
        import com.example.demo.dao.TransaksiDao;
        import com.example.demo.model.Nasabah;
        import com.example.demo.model.Transaksi;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.jpa.repository.Lock;
        import org.springframework.stereotype.Service;

        import javax.persistence.LockModeType;
        import javax.transaction.Transactional;
        import java.util.Date;
        import java.util.List;
        import java.util.Optional;

@Service
@Transactional
public class TransaksiServiceImpl implements TransaksiService{

    Logger log = LoggerFactory.getLogger(TransaksiServiceImpl.class);

    @Autowired
    private TransaksiDao transaksiDao;

    @Autowired
    private NasabahDao nasabahDao;

    @Override
    public Transaksi save(Transaksi transaksi) {
        if(transaksi.getAccountId() == null){
            log.error("Error Transaksi AccountId Is NULL");
            return null;
        }
        Long idNasabah = nasabahDao.findAccountId(transaksi.getAccountId());
        if(idNasabah == null ){
            log.error("Error Transaksi AccountId Is NOT FIND");
            return null;
        }
        Long balance = 0L;
        if(transaksi.getDebitCreditStatus().equalsIgnoreCase("d")){
            //kurang check balance > 0

            Transaksi trans = transaksiDao.findTransaksisByAccountIdOrderById(transaksi.getAccountId());
            if(trans ==null){
                return null;
            }
            balance= trans.getBalance();
            if(balance>0 && balance >= transaksi.getAmount()){
                balance = balance - transaksi.getAmount();
                transaksi.setBalance(balance);
                return transaksiDao.save(transaksi);
            }else{
                return null;
            }
        }else if(transaksi.getDebitCreditStatus().equalsIgnoreCase("c")){
            //nambah every transaksi balance nambah
            Transaksi trans = transaksiDao.findTransaksisByAccountIdOrderById(transaksi.getAccountId());
            if(trans ==null){
                transaksi.setBalance(transaksi.getAmount());
            }else {
                balance = trans.getBalance();
                balance = balance + transaksi.getAmount();
                transaksi.setBalance(balance);
            }
            return transaksiDao.save(transaksi);
        }

        return null;
    }

    @Override
    public Page<Transaksi> findAllwithPage(Pageable pageable) {
        return transaksiDao.findAll(pageable);
    }

    @Override
    @Lock(LockModeType.READ)
    public List<Transaksi> findAll() {
        return transaksiDao.findAll();
    }

    @Override
    @Lock(LockModeType.READ)
    public List<Transaksi> findTransaksiByIdAndDate(Long accountId, Date startDate, Date endDate) {
        if (startDate == null){
            startDate = new Date();
        }if(endDate == null) {
            Integer getMonthNow = new Date().getMonth();
            endDate = new Date();
            endDate.setMonth(getMonthNow + 1);
        }
        return transaksiDao.findAllByAccountIdAndTransactionDateBetween(accountId,startDate,endDate);
    }

    @Override
    @Lock(LockModeType.READ)
    public List<Transaksi> findTransaksiByAccountId(Long accountId) {
        return transaksiDao.findAllByAccountId(accountId);
    }

    @Override
    public Transaksi findTransaksiByid(Long id) {
        return transaksiDao.findTransaksiById(id);
    }
}
