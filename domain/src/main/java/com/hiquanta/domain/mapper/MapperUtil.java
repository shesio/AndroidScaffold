package com.hiquanta.domain.mapper;



import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;

import io.rx_cache.Reply;

/**
 * Created by hiquanta on 2016/10/25.
 */
public class MapperUtil {

    private static ModelMapper MAPPER = null;

    private static ModelMapper getMapper(){
        if(MAPPER == null){
            MAPPER = new ModelMapper();
            MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        }

        return MAPPER;
    }

    public static <S, T> T map(S source, Class<T> targetClass) {
        return getMapper().map(source, targetClass);
    }
    public static <S, T> Reply<T> map(Reply<S> source, Class<T> targetClass) {
       T t= getMapper().map(source.getData(), targetClass);
        Reply<T> reply=new Reply<>(t,source.getSource(),false);
        return reply;
    }

    public static <S, T> void mapTo(S source, T dist) {
        getMapper().map(source, dist);
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        List<T> list = new ArrayList<>();
        for (S s : source) {
            list.add(getMapper().map(s, targetClass));
        }
        return list;
    }
    public static <S, T> Reply<List<T>> mapList(Reply<List<S>> source, Class<T> targetClass) {
        List<T> list = new ArrayList<>();
        for (S s : source.getData()) {
            list.add(getMapper().map(s, targetClass));
        }
        Reply<List<T>> replyUserList=new Reply<>(list,source.getSource(),false);
        return replyUserList;
    }
}
