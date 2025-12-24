package com.khushi.projects.buildly.mapper;

import com.khushi.projects.buildly.dto.member.MemberResponse;
import com.khushi.projects.buildly.entity.ProjectMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    MemberResponse toMemberResponseFromMember(ProjectMember member);
}
