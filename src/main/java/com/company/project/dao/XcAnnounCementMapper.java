package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.XcAnnounCement;

import java.util.List;

public interface XcAnnounCementMapper extends Mapper<XcAnnounCement> {

    List<XcAnnounCement> list(XcAnnounCement xcAnnounCement);

}
