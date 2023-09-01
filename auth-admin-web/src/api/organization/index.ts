import request from "@/utils/request";
import { AxiosPromise } from "axios";
import { OrganizationVO, OrganizationDTO, OrganizationForm } from "./types";

/**
 * 组织树形表格
 *
 * @param queryParams
 */
export function listOrganiztion(
  queryParams?: OrganizationDTO
): AxiosPromise<OrganizationVO[]> {
  return request({
    url: "/api/v1/org/list",
    method: "post",
    data: queryParams,
  });
}
/**
 * restful post put get delete
 */
export function listOrgOptions(): AxiosPromise<[]> {
  return request({
    url: "/api/v1/org/options",
    method: "post",
  });
}
export function addOrg(data: OrganizationForm) {
  return request({
    url: "/api/v1/org/add",
    method: "post",
    data: data,
  });
}
export function getOrgForm(id: number): AxiosPromise<OrganizationForm> {
  return request({
    url: "/api/v1/org/" + id,
    method: "post",
  });
}
export function updateOrg(id: number, data: OrganizationForm) {
  return request({
    url: "/api/v1/org/" + id + "/update",
    method: "post",
    data: data,
  });
}
