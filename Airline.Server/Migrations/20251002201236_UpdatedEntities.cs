using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Airline.Server.Migrations
{
    /// <inheritdoc />
    public partial class UpdatedEntities : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "MarketType",
                table: "NetworkedRoutes",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<int>(
                name: "PlannedWeeklyFlights",
                table: "NetworkedRoutes",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<DateTime>(
                name: "BoardingTime",
                table: "Flights",
                type: "datetime(6)",
                nullable: false,
                defaultValue: new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified));

            migrationBuilder.AddColumn<DateTime>(
                name: "TakeoffTime",
                table: "Flights",
                type: "datetime(6)",
                nullable: false,
                defaultValue: new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified));

            migrationBuilder.AddColumn<int>(
                name: "MarketType",
                table: "Airports",
                type: "int",
                nullable: false,
                defaultValue: 0);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "MarketType",
                table: "NetworkedRoutes");

            migrationBuilder.DropColumn(
                name: "PlannedWeeklyFlights",
                table: "NetworkedRoutes");

            migrationBuilder.DropColumn(
                name: "BoardingTime",
                table: "Flights");

            migrationBuilder.DropColumn(
                name: "TakeoffTime",
                table: "Flights");

            migrationBuilder.DropColumn(
                name: "MarketType",
                table: "Airports");
        }
    }
}
