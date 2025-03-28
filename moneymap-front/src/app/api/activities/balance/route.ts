import { backendApi } from "@/lib/api";
import { AxiosError } from "axios";
import { NextRequest } from "next/server";


type BackendResponseErrorType = {
    timestamp: string;
    status: number;
    error: string;
    path: string;
  };

type GetActivitiesBackendResponse = {
    balance: number;
}


export async function GET(request: NextRequest){

    const authToken = request.cookies.get("moneymap.token")?.value;

    if (!authToken) {
      return new Response(JSON.stringify(new Error("Usuário não autorizado")), {
        status: 401,
      });
    }

    try{

        const result = await backendApi("/activities/balance", {
            headers: {
                "Authorization": `Bearer ${authToken}`
            }
        });

        const balance = result.data as GetActivitiesBackendResponse;

        return new Response(JSON.stringify(balance), {status: 200})

    }
    catch(e){
         const axiosError = e as AxiosError;
        
            const {status, error} = axiosError.response?.data as BackendResponseErrorType;
        
            if(status){
              return new Response(
                JSON.stringify(new AxiosError(error, status.toString())),
                { status }
              );
            } else {
              return new Response(
                JSON.stringify(new AxiosError(axiosError.message, axiosError.code)),
                {
                  status: axiosError.status || 500,
                }
              );
            }
    }

}