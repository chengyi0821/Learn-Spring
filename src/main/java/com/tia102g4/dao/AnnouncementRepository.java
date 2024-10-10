package com.tia102g4.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tia102g4.model.Announcement;

@Repository
public class AnnouncementRepository implements AnnouncementDaoCustom{

	@Autowired
    private EntityManager entityManager; 

	@Override
    public List<Announcement> findByCompositeQuery(Map<String, String> map) {
        // 創建各種查詢條件
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        // 指定查詢的返回類型為 Anno
        CriteriaQuery<Announcement> criteria = builder.createQuery(Announcement.class);
        // 表示要查詢的類型為 Anno
        Root<Announcement> root = criteria.from(Announcement.class);
        // 創建用於存放查詢條件的集合
        List<Predicate> predicates = new ArrayList<>();
        // 起始日期跟結束日期之間的查詢
        if (map.containsKey("startDate") && map.containsKey("endDate")) {
            Predicate startDate = builder.greaterThanOrEqualTo(root.get("startDate"),
                    Date.valueOf(map.get("startDate")));
            Predicate endDate = builder.lessThanOrEqualTo(root.get("endDate"),
                    Date.valueOf(map.get("endDate")));
            predicates.add(builder.and(startDate, endDate));
        } else {
            if (map.containsKey("startDate")) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("startDate"),
                        Date.valueOf(map.get("startDate"))));
            }
            if (map.containsKey("endDate")) {
                predicates.add(builder.lessThanOrEqualTo(root.get("endDate"),
                        Date.valueOf(map.get("endDate"))));
            }
        }
        // 主旨模糊搜尋
        if (map.containsKey("searchQuery")) {
            predicates.add(builder.like(root.get("heading"), "%" + map.get("searchQuery") + "%"));
        }

        // 只能查詢沒有刪除的資料
        predicates.add(builder.isFalse(root.get("deleted")));

        // 將所有的 predicates 條件使用 AND 邏輯組合，並設置為 criteria 的 WHERE 條件
        criteria.where(builder.and(predicates.toArray(new Predicate[0])));
        // 針對 ID 做升冪排序
        criteria.orderBy(builder.asc(root.get("annoId")));

        TypedQuery<Announcement> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }
}
