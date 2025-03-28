"use client";

import {
  CustomAlert,
  CustomAlertType,
} from "@/components/general/custom-alert";
import { Button } from "@/components/ui/button";
import { Calendar } from "@/components/ui/calendar";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { ActivityTableContext } from "@/context/activity-table-context";
import { frontendApi } from "@/lib/api";
import { cn } from "@/lib/utils";
import { zodResolver } from "@hookform/resolvers/zod";
import { AxiosError } from "axios";
import { format } from "date-fns";
import { CalendarIcon } from "lucide-react";
import { JSX, useContext, useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";

enum ActivityType {
  REEVENUE = "revenue",
  EXPENSE = "expense",
}

const insertFormSchema = z.object({
  date: z.date({ required_error: "Insira uma data" }),
  description: z
    .string({ required_error: "Insira uma descrição" })
    .min(3, { message: "Insira pelo menos 3 caracteres" }),
  value: z.coerce
    .number({ required_error: "Insira um valor" })
    .min(0.01, { message: "O valor precisa ser positivo" }),
  type: z.nativeEnum(ActivityType, { required_error: "Selecione um tipo" }),
});

type InsertFormType = z.infer<typeof insertFormSchema>;

export function InsertActivityForm() {
  const [insertMessage, setInsertMessage] = useState<JSX.Element>(<></>);

  const activityTableContext = useContext(ActivityTableContext);

  const insertForm = useForm<InsertFormType>({
    resolver: zodResolver(insertFormSchema),
    defaultValues: {
      date: new Date(),
      description: "",
      // @ts-expect-error
      value: "",
      type: ActivityType.REEVENUE,
    },
  });

  async function onInsertFormSubmit({
    date,
    description,
    value,
    type,
  }: InsertFormType) {
    const formatedData = JSON.stringify({
      date: date.toISOString(),
      description,
      value,
      type,
    });

    try {
      const result = await frontendApi.post("/activities", formatedData);

      const message = (
        <CustomAlert
          title="Atividate Inserida com Sucesso!"
          message={`A ${type} foi inserida com sucesso!`}
          type={CustomAlertType.SUCCESS}
        />
      );

      setInsertMessage(message);

      activityTableContext.refreshTable();
    } catch (e) {
      const axiosError = e as AxiosError;

      const data = axiosError.response?.data as {
        message: string;
        code: number;
      };

      var errorMessage;

      if (data) {
        errorMessage = data.message;
      } else {
        errorMessage = axiosError.message;
      }

      const message = (
        <CustomAlert
          title={`Erro ao inserir a atividade!`}
          message={`${errorMessage}.`}
          type={CustomAlertType.ERROR}
        />
      );

      setInsertMessage(message);
    }

    setTimeout(() => setInsertMessage(<></>), 1500);
  }

  return (
    <div className="flex space-x-2 p-4">
      <Form {...insertForm}>
        <form
          onSubmit={insertForm.handleSubmit(onInsertFormSubmit)}
          className="flex gap-1.5"
        >
          <FormField
            control={insertForm.control}
            name="date"
            render={({ field }) => {
              return (
                <FormItem>
                  <Popover>
                    <PopoverTrigger asChild>
                      <Button
                        variant={"outline"}
                        className={cn(
                          "w-[280px] justify-start text-left font-normal",
                          !field.value && "text-muted-foreground"
                        )}
                      >
                        <CalendarIcon className="mr-2 h-4 w-4" />
                        {field.value ? (
                          format(field.value, "PPP")
                        ) : (
                          <span>Pick a date</span>
                        )}
                      </Button>
                    </PopoverTrigger>
                    <PopoverContent className="w-auto p-0">
                      <Calendar
                        mode="single"
                        selected={field.value}
                        onSelect={field.onChange}
                        initialFocus
                        className="bg-blue-300"
                      />
                    </PopoverContent>
                  </Popover>
                </FormItem>
              );
            }}
          />

          <FormField
            control={insertForm.control}
            name="description"
            render={({ field }) => {
              return (
                <FormItem>
                  <FormControl>
                    <Input
                      type="text"
                      placeholder="Descrição"
                      {...field}
                      className="w-md"
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              );
            }}
          />

          <FormField
            control={insertForm.control}
            name="value"
            render={({ field }) => {
              return (
                <FormItem>
                  <FormControl>
                    <Input type="number" placeholder="Valor" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              );
            }}
          />

          <FormField
            control={insertForm.control}
            name="type"
            render={({ field }) => {
              return (
                <FormItem>
                  <Select
                    onValueChange={field.onChange}
                    defaultValue={field.value}
                  >
                    <FormControl>
                      <SelectTrigger className="w-[180px]">
                        <SelectValue placeholder="Tipo" />
                      </SelectTrigger>
                    </FormControl>
                    <SelectContent>
                      <SelectItem
                        value={ActivityType.REEVENUE}
                        className="bg-amber-100"
                      >
                        Entrada
                      </SelectItem>
                      <SelectItem
                        value={ActivityType.EXPENSE}
                        className="bg-amber-100"
                      >
                        Saida
                      </SelectItem>
                    </SelectContent>
                  </Select>
                  <FormMessage />
                </FormItem>
              );
            }}
          />

          <Button type="submit" className="bg-amber-200 hover:bg-blue-500">
            Incluir
          </Button>
        </form>
        {insertMessage}
      </Form>
    </div>
  );
}
