package com.sdu.web.sys_role.entity;


import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/4 8:03
 */
@Data
public class RolePara {
    private Long currentPage;
    private Long pageSize;
    private String roleName;
}
