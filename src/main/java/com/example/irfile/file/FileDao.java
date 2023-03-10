package com.example.irfile.file;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileDao {
    Long saveFile(FileDto fileDto);
    Long selectLastFileSeq();
}
