"use client";

import { ActivityTableContext } from "@/context/activity-table-context";
import { useContext } from "react";

export function Balance() {
  const activityTableContext = useContext(ActivityTableContext);

  const balance = activityTableContext.balance;

  var className = "text-emerald-800";

  if (balance < 0) {
    className = "text-red-700";
  }

  return (
    <>
      <div className="flex gap-4 p-4 text-xl font-bold">
        <p>Total: </p>
        <p className={className}>R$ {balance}</p>
      </div>
    </>
  );
}
