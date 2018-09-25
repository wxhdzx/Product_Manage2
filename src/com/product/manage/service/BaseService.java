package com.product.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;

@Service
/**
 * ͨ��service
 * @author dell
 *
 * @param <T>
 */
public abstract class BaseService<T> {
	@Autowired
	private Mapper<T> mapper;
	//ͨ��id��ѯ
	public T queryById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	//ͨ��idɾ��
	public Integer deleteById(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}
	//��ѯ����
	public List<T> queryAll(){
		return mapper.select(null);
	}
	//����ĳ��������ѯһ����¼
	public T queryByRecord(T record) {
		return mapper.selectOne(record);
	}
	//��������
	public Integer save(T t) {
		return mapper.insert(t);
	}
	//����ĳ�������޸�����
	public Integer update(T record) {
		return mapper.updateByPrimaryKey(record);
	}
	
}
