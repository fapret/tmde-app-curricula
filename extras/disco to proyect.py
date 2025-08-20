import pandas as pd
from datetime import datetime

# Input and output filenames
input_csv = "students_2018.csv"
output_csv = "output.csv"

# Read the CSV
df = pd.read_csv(input_csv)

# Rename and keep only the required columns
df = df.rename(columns={
    "Case ID": "ID",
    "Complete Timestamp": "Timestamp"
})[
    ["ID","Activity","Timestamp","Career","Plan",
     "Curricular Unit","Course Edition","Course Year","Grade","Credits"]
]

# Convert timestamp format
def convert_ts(ts):
    dt = datetime.strptime(ts, "%Y-%m-%d %H:%M:%S.%f")
    # Add UTC as timezone string
    return dt.strftime("%a %b %d %H:%M:%S UTC %Y")

df["Timestamp"] = df["Timestamp"].apply(convert_ts)

# Save back to CSV
df.to_csv(output_csv, index=False)

print(f"✅ Converted CSV saved to {output_csv}")