import request from "@/utils/request";
import { AxiosPromise } from "axios";
import { OrganizationVO, OrganizationDTO } from "./types";

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
    params: queryParams,
  });
}
