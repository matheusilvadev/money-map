import { Alert, AlertTitle, AlertDescription } from "@/components/ui/alert";

export const enum CustomAlertType {
  SUCCESS = "success",
  ERROR = "error",
}

export type CustomAlertPops = {
  type: CustomAlertType.SUCCESS | CustomAlertType.ERROR;
  title: string;
  message: string;
};

export function CustomAlert({ type, title, message }: CustomAlertPops) {

    const alert = type === CustomAlertType.SUCCESS 
    ? <SuccessAlert title={title} message={message} /> 
    : <ErrorAlert title={title} message={message}/>;

  return alert;
}

type SuccessAlertProps = {
  title: string;
  message: string;
};

function SuccessAlert({ title, message }: SuccessAlertProps) {
  return (
    <Alert className="flex-row border-emerald-500">
      <AlertTitle className="font-bold px-4">{title}</AlertTitle>
      <AlertDescription className="font-thin px-4">{message}</AlertDescription>
    </Alert>
  );
}

type ErrorAlertProps = {
  title: string;
  message: string;
};

function ErrorAlert({ title, message }: ErrorAlertProps) {
    return (
      <Alert className="flex-row border-red-500">
        <AlertTitle className="font-bold px-4">{title}</AlertTitle>
        <AlertDescription className="font-thin px-4">{message}</AlertDescription>
      </Alert>
    );
  }
