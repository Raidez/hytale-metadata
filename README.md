# Metadata

Metadata is a mod that lets you define and manage metadata for any asset in the game.

This is useful for modders who want to attach custom properties to their assets without modifying the original asset files.

## Adding metadata to an asset

Add a `Metadata` object to your asset file and define your properties there.

Each property must declare a `Type` (`string`, `number`, or `boolean`) and a `Default` value.

```json
{
    "Metadata": {
        "Base": {
            "Type": "string",
            "Default": "Hello"
        },
        "Name": {
            "Type": "string",
            "Default": "World"
        },
        "Full": {
            "Type": "string",
            "Default": "Hello World"
        }
    },

    ... rest of the asset content
}
```

## Changing metadata values

The mod adds a new interaction, `ChangeMetadata`, to update metadata values.

| Operation    | Description                                              | Supported types |
| ------------ | -------------------------------------------------------- | --------------- |
| `Set`        | Set the metadata value to a specific value.              | all             |
| `Reset`      | Reset the metadata value to its default value.           | all             |
| `CopyFrom`   | Copy the metadata value from another key.                | all             |
| `Increment`  | Increment the metadata value by a specific amount.       | `number`        |
| `Decrement`  | Decrement the metadata value by a specific amount.       | `number`        |
| `Toggle`     | Toggle the metadata value.                               | `boolean`       |
| `Concat`     | Concatenate the metadata value with a specific value.    | `string`        |
| `ConcatFrom` | Concatenate the metadata value with another key's value. | `string`        |

```json
{
    "Type": "ChangeMetadata",
    "Target": "Self",
    "Operation": "Set",
    "Key": "Name",
    "Value": "Raidez"
}
```

## Branching on metadata values

Use `MatchMetadata` to branch your logic based on the current value of a metadata key.

| Matcher              | Description                                                      | Supported types |
| -------------------- | ---------------------------------------------------------------- | --------------- |
| `Equals`             | Match if the metadata value equals a specific value.             | all             |
| `NotEquals`          | Match if the metadata value does not equal a specific value.     | all             |
| `GreaterThan`        | Match if the metadata value is greater than a specific value.    | `number`        |
| `LessThan`           | Match if the metadata value is less than a specific value.       | `number`        |
| `GreaterThanOrEqual` | Match if the metadata value is greater than or equal to a value. | `number`        |
| `LessThanOrEqual`    | Match if the metadata value is less than or equal to a value.    | `number`        |
| `IsTrue`             | Match if the metadata value is true.                             | `boolean`       |
| `IsFalse`            | Match if the metadata value is false.                            | `boolean`       |
| `Contains`           | Match if the metadata value contains a specific value.           | `string`        |
| `NotContains`        | Match if the metadata value does not contain a specific value.   | `string`        |
| `StartsWith`         | Match if the metadata value starts with a specific value.        | `string`        |
| `EndsWith`           | Match if the metadata value ends with a specific value.          | `string`        |
| `Regex`              | Match if the metadata value matches a regex pattern.             | `string`        |
| `Empty`              | Match if the metadata value is empty.                            | `string`        |
| `NotEmpty`           | Match if the metadata value is not empty.                        | `string`        |

The interaction also supports an `Else` property, defining what happens when none of the matchers match.

```json
{
    "Type": "MatchMetadata",
    "Target": "Self",
    "Key": "Name",
    "Matchers": [
        {
            "Type": "NotEmpty",
            "Next": [
                {
                    "Type": "ChangeMetadata",
                    "Target": "Self",
                    "Operation": "Set",
                    "Key": "Full",
                    "Value": ""
                },
                {
                    "Type": "ChangeMetadata",
                    "Target": "Self",
                    "Operation": "ConcatFrom",
                    "Key": "Full",
                    "Value": "Base"
                },
                {
                    "Type": "ChangeMetadata",
                    "Target": "Self",
                    "Operation": "Concat",
                    "Key": "Full",
                    "Value": " "
                },
                {
                    "Type": "ChangeMetadata",
                    "Target": "Self",
                    "Operation": "ConcatFrom",
                    "Key": "Full",
                    "Value": "Name"
                }
            ]
        }
    ]
}

```

## Command meta for manipulating metadata

The mod also adds a new command, `metadata` (also `meta` alias), to manipulate metadata values via commands.

`/metadata <operation> <key> [value]`

| Operation | Description                                    |
| --------- | ---------------------------------------------- |
| `get`     | Get the metadata value.                        |
| `set`     | Set the metadata value to a specific value.    |
| `reset`   | Reset the metadata value to its default value. |
