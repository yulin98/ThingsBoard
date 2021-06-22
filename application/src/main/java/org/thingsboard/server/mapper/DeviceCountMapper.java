package org.thingsboard.server.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeviceCountMapper {

    @Select("WITH RECURSIVE views AS (\n" +
            "    SELECT from_type, from_id, to_type, to_id\n" +
            "    FROM relation\n" +
            "    WHERE from_id = #{assetId}\n" +
            "    UNION\n" +
            "    SELECT r.from_type, r.from_id, r.to_type, r.to_id\n" +
            "    FROM relation r\n" +
            "             INNER JOIN views v ON v.to_id = r.from_id\n" +
            ") \n" +
            "select distinct to_id FROM views v WHERE v.to_type = 'DEVICE'")
    public List<String> deviceCountByAssetId(String assetId);

}
