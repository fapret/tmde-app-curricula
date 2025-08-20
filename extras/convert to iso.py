import pandas as pd

# Load your CSV
df = pd.read_csv("4a0da1fe-a687-4717-ab9e-d61fd94fac4b.csv")

# Assuming your timestamp column is called "Timestamp"
df["Timestamp"] = pd.to_datetime(
    df["Timestamp"], 
    format="%a %b %d %H:%M:%S %Z %Y"
)

# Convert to Disco-friendly string
df["Timestamp"] = df["Timestamp"].dt.strftime("%Y-%m-%d %H:%M:%S")

# Save back
df.to_csv("converted_4a0da1fe-a687-4717-ab9e-d61fd94fac4b.csv", index=False)