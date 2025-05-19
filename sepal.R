# EX. NO: 5 - VISUALIZE DATA USING ANY PLOTTING FRAMEWORK
# AIM: To visualize data using ggplot2 in R

# Step 1: Install Required Packages (Run only once)
install.packages("ggplot2")
install.packages("caret")

# Step 2: Load Libraries
library(ggplot2)
library(caret)

# Step 3: Load the built-in iris dataset
data(iris)

# -------- Scatterplot with ggplot2 (qplot) --------
# Basic scatterplot: Sepal.Length vs Petal.Length
qplot(Sepal.Length, Petal.Length, data = iris)

# Colored by Species
qplot(Sepal.Length, Petal.Length, data = iris, color = Species)

# Add Petal.Width as size and reduce opacity
qplot(Sepal.Length, Petal.Length, data = iris, color = Species, size = Petal.Width, alpha = I(0.7))

# Add axis labels and title
qplot(Sepal.Length, Petal.Length, data = iris, color = Species,
      xlab = "Sepal Length", ylab = "Petal Length",
      main = "Sepal vs. Petal Length in Fisher's Iris Data")

# -------- Univariate Histograms --------
# Show histogram for each attribute
par(mfrow = c(1, 4))  # 1 row, 4 columns layout
for (i in 1:4) {
  hist(iris[, i], main = names(iris)[i], col = "skyblue")
}
par(mfrow = c(1, 1))  # reset layout

# -------- Scatterplot Matrix --------
pairs(iris, main = "Scatterplot Matrix of Iris Dataset", col = iris$Species)

# -------- Density Plots --------
featurePlot(x = iris[, 1:4], y = iris[, 5],
            plot = "density",
            scales = list(x = list(relation = "free"), y = list(relation = "free")),
            auto.key = list(columns = 3))
